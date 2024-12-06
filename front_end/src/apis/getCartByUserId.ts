import httpRequest from '@/request/index'

// 定义接口的传参
// interface UserInfoParam {
// 	userName: string,
// }

// 
export function apiGetCartByUserId(userId:number) {
    return httpRequest({
		url: `http://localhost:8083/cart/list/${userId}`,
		method: 'get',
	})
}