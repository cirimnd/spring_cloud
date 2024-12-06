import httpRequest from '@/request/index'

// 定义接口的传参
// interface UserInfoParam {
// 	userName: string,
// }

// 
export function apiGetOrderByUserId(userId:number) {
    return httpRequest({
		url: `http://localhost:8081/order/user/${userId}`,
		method: 'get',
	})
}