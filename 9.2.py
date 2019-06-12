from random import randint
from copy import deepcopy
import sys
infile = sys.stdin.read().split('\n')

def CreateAdjacencyList(infile):
    
    adj_list = {}
    circuit_max = 0
    for line in infile:
        node = line.strip('\n')
        node = node.replace(' -> ', ' ')
        node = node.split(' ')
        adj_list.setdefault(node[0], [])
        for number in node[1].split(','):
            adj_list[node[0]].append(number)
            circuit_max += 1
    
    return adj_list, circuit_max

def FindEulerianCycle(adj_list, circuit_max, start, curr_vrtx, path):
    red_adj_list = {}            
    red_adj_list = deepcopy(adj_list)            

    stack = []
    circuit = []    
    while len(circuit) != circuit_max:
        try:
            if red_adj_list[curr_vrtx] != []:
                stack.append(curr_vrtx)
                pick = randint(0,len(red_adj_list[curr_vrtx])-1)
                temp = deepcopy(curr_vrtx)
                curr_vrtx = red_adj_list[temp][pick]
                red_adj_list[temp].remove(curr_vrtx)
        
            else:
                circuit.append(curr_vrtx)
                curr_vrtx = stack[len(stack)-1]
                stack.pop()
        except KeyError:
            circuit.append(curr_vrtx)
            curr_vrtx = stack[len(stack)-1]
            stack.pop()

    path = start + '->'
    for vrtx in circuit[::-1]:
        path += (vrtx + '->')
    return path.strip('->')

def FindStart(red_adj_list):
    
    start = {}
    for one in red_adj_list:
        start.setdefault(one, 0)
        start[one] += len(red_adj_list[one])

    end = {}
    for one in red_adj_list:
        for two in red_adj_list[one]:
            end.setdefault(two, 0)
            end[two] += 1

    for one in end:
        try: 
            if start[one] != end[one]:
                if start[one] > end[one]:
                    start_node = one
                if start[one] < end[one]:
                    end_node = one
        except KeyError:
            end_node = one

    for one in start:
        try:
            if end[one] != start[one]:
                if end[one] < start[one]:
                    start_node = one
                if end[one] > start[one]:
                    end_node = one
        except KeyError:
            start_node = one

    red_adj_list[end_node] = []
    
    return red_adj_list, start_node

def FindEulerianPath(infile):
    adj_list, circuit_max = CreateAdjacencyList(infile)
    red_adj_list = {}            
    red_adj_list = deepcopy(adj_list)
    
    red_adj_list, start_node= FindStart(red_adj_list)
    
    start = start_node
    curr_vrtx = start_node
    path = [curr_vrtx]

    return FindEulerianCycle(adj_list, circuit_max, start, curr_vrtx, path)

print(FindEulerianPath(infile))
