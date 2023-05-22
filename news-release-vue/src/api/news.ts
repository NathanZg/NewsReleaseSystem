import httpService from "@/request"
export function newsAdd(news: object) {
    return httpService({
        url: 'newsAdd',
        method: 'post',
        data: news
    })
}

export function newsDelete(ids: string) {
    return httpService({
        url: 'newsDelete',
        method: 'delete',
        data: ids,
        headers: {
            'Content-Type': 'text/plain'
        }
    })
}

export function newsSelect(queryVo: object) {
    return httpService({
        url: 'newsSelect',
        method: 'post',
        data: queryVo
    })
}

export function newsUpdate(news: object) {
    return httpService({
        url: 'newsUpdate',
        method: 'post',
        data: news
    })
}

export function getNewsByType(id: string) {
    return httpService({
        url: '/selectNewsByType',
        method: 'post',
        data: id,
        headers: {
            'Content-Type': 'text/plain'
        }
    })
}