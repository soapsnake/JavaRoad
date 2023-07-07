# coding=utf-8


# 解法复杂度过高,但是实现是没有问题的!!!!!!!!!!!!
class Solution:

    def findAnagrams(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: List[int]
        """
        if s == "" or p == "":
            return []
        resu = []
        for i in range(0, len(s) - len(p) + 1):
            str1 = s[i:len(p) + i]
            resu.append(i)
            temp = list(str1)
            for l in p:
                if l not in temp:
                    resu.remove(i)
                    break
                else:
                    temp.remove(l)
            if len(temp) != 0:
                try:
                    resu.remove(i)
                except:
                    pass
        return resu


sp = Solution()
res = sp.findAnagrams("abcahidjsaijdabcjasidjsa", "abc")
print("this is a python file")
print(res)
