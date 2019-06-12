from collections import defaultdict
import sys
k = input()
kmers = sys.stdin.read().split()

composition = kmers

def deBruijnGraph(kmers):
    graph = {}
    for i in range(len(kmers)):
        try:
            graph[kmers[i][:-1]].append(kmers[i][1:])
        except:
            graph[kmers[i][:-1]] = [kmers[i][1:]]
    return graph

graph = deBruijnGraph(composition)

degrees = defaultdict(int)
for k in graph:
    for v in graph[k]:
        degrees[k] += 1
        degrees[v] -= 1
source = [k for k, v in degrees.items() if v == 1][0]
sinc = [k for k, v in degrees.items() if v == -1][0]
list(graph)
start = list(graph)[0]

if sinc in graph.keys():
    graph[sinc].append(source)
else:
    graph[sinc] = [source]

cycles = {}
while graph:
    current = next(iter(graph))
    cycle = [current]
    cycles[current] = cycle
    while current in graph:
        _next = graph[current][0]
        del graph[current][0]
        if len(graph[current]) == 0:
            del graph[current]
        current = _next
        cycle.append(_next)


def traverse(tree, root):
    out = []
    for r in tree[root]:
        if r != root and r in tree:
            out += traverse(tree, r)
        else:
            out.append(r)
    return out

cycle = traverse(cycles, start)
for i in range(1, len(cycle)):
    if cycle[i-1] == sinc and cycle[i] == source:
        boarder = i
path = cycle[boarder:]+cycle[1:boarder]
print (*((([s[0] for s in list(path)]) + list(path[-1][1:]))),sep = '')
