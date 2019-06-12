from numpy import zeros

s = input()
t = input()

M = zeros((len(s)+1,len(t)+1), dtype=int)
for i in range(1,len(s)+1):
	M[i][0]= i
for i in range(1,len(t)+1):
	M[0][i]= i

for i in range(1,len(s)+1):
	for j in range(1,len(t)+1):
		if s[i-1] == t[j-1]:
			M[i][j] = M[i-1][j-1]
		else:
			M[i][j] = min(M[i-1][j]+1,M[i][j-1]+1, M[i-1][j-1]+1)

print(M[len(s)][len(t)])
