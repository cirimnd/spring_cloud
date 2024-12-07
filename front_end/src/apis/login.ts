// 导入axios实例
import httpRequest from '@/request/index'

// 定义接口的传参
interface UserInfoParam {
	userName: string,
	password: string
}

// 获取用户信息
export function apiGetUserInfo(param: UserInfoParam) {
    return httpRequest({
		url: 'http://localhost:9999/user/login',
		method: 'post',
		data: param,
	})
}
