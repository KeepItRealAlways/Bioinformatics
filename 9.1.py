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

def FindEulerianCycle(infile):
    adj_list, circuit_max = CreateAdjacencyList(infile)

    red_adj_list = {}            
    red_adj_list = deepcopy(adj_list)            
            
    start = '6'
    curr_vrtx = '6'

    stack = []
    circuit = []    
    while len(circuit) != circuit_max:
        
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

    path = start + '->'
    for vrtx in circuit[::-1]:
        path += (vrtx + '->')
    return path.strip('->')

print(FindEulerianCycle(infile))
