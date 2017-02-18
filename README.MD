# git使用方法
## git的安装
1.sudo add-apt-repository ppa:git-core/ppa
2.sudo apt-get update
3.sudo apt-get install git
4.git --version

## 配置ｇｉｔ的一些参数
1.git config --global user.name "51721198"
2.git config --global user.email "51721198@qq.com"
3.git config --global core.editor "vim"
4.git config --list

## 配置和github通信的ＲＳＡ－ＫＥＹ
1.切换到root:su root　　　　//不切换产生的ｋｅｙ不能用，sudo也不行
2.ssh-keygen -t rsa
3.把rsa的pub-key复制设置到github的ssh里面
4.尝试链接下看有没有问题：sudo ssh -T git@github.com   //成功会有提示

##把github上面的代码拉到本地，新搞一个项目，然后上传这个新项目
1.sudo git clone https://github.com/51721198/JavaRoad.git  //注意了，因为是用的clone方式，因此不用sudo git init,如果不是clone的话就得执行了
2.在同位置新建立一个newproject项目
3.sudo git add newproject
4.sudo git commit   //命令执行后会进入vim让你给这次提交写个描述
5.sudo git remote add origin https://github.com/51721198/JavaRoad.git
6.sudo git pull origin master    //提交代码前先更新一下
7.sudo git push origin master


 
