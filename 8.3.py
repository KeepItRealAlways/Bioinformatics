import sys
fin = sys.stdin.read().split('\n')
reads=[]

for line in fin:
    reads.append(line.replace('\n',''))
reads.sort()
for r1 in reads:
    for r2 in reads:
        if r1[1:]==r2[:len(r1)-1]:
            print(r1+' -> '+r2)
