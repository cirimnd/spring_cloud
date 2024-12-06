import httpRequest from '@/request/index'

// 定义接口的传参
interface UserInfoParam {
	userName: string,
	password: string,
    phone: string,
    role: string
}

// 
export function apiRegister(param: UserInfoParam) {
    return httpRequest({
		url: 'http://localhost:8082/user/add',
		method: 'post',
		data: param,
	})
}