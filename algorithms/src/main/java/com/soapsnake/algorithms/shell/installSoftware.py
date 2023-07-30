import subprocess


def install_homebrew():
    """
    安装 Homebrew 包管理工具
    """
    try:
        subprocess.run(
            '/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"',
            shell=True, check=True)
        print("Homebrew 安装成功！")
    except subprocess.CalledProcessError as e:
        print("Error:", e)


def is_software_installed(software):
    """
    检查软件是否已安装
    :param software: 要检查的软件名称
    :return: True（已安装）或 False（未安装）
    """
    try:
        subprocess.run(["brew", "list", software], stdout=subprocess.PIPE, stderr=subprocess.PIPE, check=True)
        return True
    except subprocess.CalledProcessError:
        return False


def install_software(software_list):
    """
    批量安装软件
    :param software_list: 要安装的软件列表
    """
    try:
        for software in software_list:
            if is_software_installed(software):
                # print(f"{software} 已安装，将卸载并重新安装。")
                # subprocess.run(["brew", "uninstall", "--zap", "--force", software], check=True)
                # print(f"{software} 完全卸载成功！")
                print(f"{software} 已安装,直接跳过。")
            else:
                subprocess.run(["brew", "install", software], check=True)
                print(f"{software} 安装成功！")
    except subprocess.CalledProcessError as e:
        print("Error:", e)


def enable_autostart(software_list):
    """
    设置软件自启动
    :param software_list: 要设置自启动的软件列表
    """
    try:
        for software in software_list:
            subprocess.run(["brew", "services", "start", software], check=True)
            print(f"{software} 设置为自启动成功！")
    except subprocess.CalledProcessError as e:
        print("Error:", e)


# 用法示例
if __name__ == "__main__":
    # 检查是否安装了 Homebrew，如果没有安装，则安装它
    try:
        subprocess.run(["brew", "--version"], stdout=subprocess.PIPE, stderr=subprocess.PIPE, check=True)
    except subprocess.CalledProcessError:
        install_homebrew()

    # 要安装的软件列表
    software_list = ["redis", "kafka", "cassandra", "etcd", "git", "maven", "gradle"]  # 你可以根据需求添加更多软件

    # 安装软件
    install_software(software_list)

    # 设置软件自启动
    enable_autostart(software_list)
