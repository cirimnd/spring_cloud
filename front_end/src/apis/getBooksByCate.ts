import httpRequest from '@/request/index'

// 定义接口的传参
// interface UserInfoParam {
// 	userName: string,
// }

// 
export function apiGetBooksByCate(categorys:string) {
    return httpRequest({
		url: `http://localhost:9999/book/cata/${categorys}`,
		method: 'get',
	})
}