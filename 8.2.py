import sys
inp = sys.stdin.read().split('\n')
reads = []
for line in inp:
    reads.append(line.replace('\n',''))
k=len(reads[0])
print(reads[0], end='')
for r in reads[1:]:
    print(r[k-1:], end='')
