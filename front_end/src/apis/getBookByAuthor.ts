import httpRequest from '@/request/index'

// 定义接口的传参
// interface UserInfoParam {
// 	userName: string,
// }

// 
export function apiGetBookByAuthor(athName:string) {
    return httpRequest({
		url: `http://localhost:9999/book/author?author=${athName}`,
		method: 'get',
	})
}