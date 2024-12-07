import httpRequest from '@/request/index'

// 定义接口的传参
interface UserInfoParam {
    userid: number,
	userName: string,
	password: string,
    phone: string
}

// 
export function apiUpdateUser(param: UserInfoParam) {
    return httpRequest({
		url: 'http://localhost:9999/user/edit',
		method: 'put',
		data: param,
	})
}