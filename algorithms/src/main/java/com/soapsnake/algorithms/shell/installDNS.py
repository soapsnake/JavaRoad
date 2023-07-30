import subprocess
import re


def get_connected_wifi_name():
    """
    获取当前连接的WiFi名称
    :return: WiFi名称，如果未连接到Wi-Fi，则返回None
    """
    try:
        output = subprocess.check_output(
            ["/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport", "-I"])
        wifi_info = output.decode("utf-8")
        lines = wifi_info.split("\n")
        for line in lines:
            if " SSID:" in line:
                wifi_name = line.split(":", 1)[1].strip()
                return wifi_name
    except subprocess.CalledProcessError as e:
        print("Error:", e)
    return None


def set_dns_servers(interface, dns_servers):
    """
    设置指定网络接口的DNS服务器
    :param interface: 网络接口名称，例如"Wi-Fi"或"Ethernet"
    :param dns_servers: 要设置的DNS服务器列表，例如["8.8.8.8", "8.8.4.4"]
    """
    dns_servers_str = " ".join(dns_servers)
    cmd = f"networksetup -setdnsservers {interface} {dns_servers_str}"
    subprocess.run(cmd, shell=True)


def clear_dns_servers(interface):
    """
    清除指定网络接口的DNS服务器设置
    :param interface: 网络接口名称，例如"Wi-Fi"或"Ethernet"
    """
    cmd = f"networksetup -setdnsservers {interface} empty"
    subprocess.run(cmd, shell=True)


def get_current_dns_servers():
    """
    获取当前本机的 DNS 服务器列表
    :return: DNS 服务器列表，如果获取失败则返回空列表
    """
    try:
        output = subprocess.check_output("scutil --dns", shell=True)
        output = output.decode("utf-8")
        print(output)

        # 查找 DNS 服务器地址的行，并提取其中的 IP 地址
        dns_servers = []
        for line in output.splitlines():
            if "nameserver" in line and "." in line:
                dns_server = line.strip().split()[-1]
                dns_servers.append(dns_server)

        return dns_servers
    except subprocess.CalledProcessError as e:
        print("Error:", e)
        return []

# 用法示例
if __name__ == "__main__":
    wifi_name_to_check = "Buffalo-A-3C80"  # 用你的WiFi名称替换这里
    interface_name = "Wi-Fi"  # 可能需要根据你的网络接口名称进行调整

    current_wifi_name = get_connected_wifi_name()
    if current_wifi_name == wifi_name_to_check:
        dns_servers_to_set = ["8.8.8.8", "1.1.1.1"]
        set_dns_servers(interface_name, dns_servers_to_set)
        print(f"DNS设置成功！当前连接的WiFi名称：{current_wifi_name}")
    else:
        print(f"不满足居家条件，将对DNS进行清理。当前连接的WiFi名称：{current_wifi_name}")
        clear_dns_servers(interface_name)
    dns_servers = get_current_dns_servers()
    print("-----dns servers-----")
    print(dns_servers)
    print("-----dns servers----")
    subprocess.run("nslookup www.google.com", shell=True)

