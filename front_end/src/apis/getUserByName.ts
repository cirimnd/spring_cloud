import httpRequest from '@/request/index'

// 定义接口的传参
// interface UserInfoParam {
// 	userName: string,
// }

// 
export function apiGetUserByName(username:string) {
    return httpRequest({
		url: `http://localhost:8082/user/name?name=${username}`,
		method: 'get',

	})
}