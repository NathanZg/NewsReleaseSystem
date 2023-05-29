import httpService from "@/request"
export function pageQueryCommentByCondition(queryVo: object) {
    return httpService({
        url: '/comment/pageQueryByCondition',
        method: 'post',
        data: queryVo
    })
}

export function insertComment(comment: object) {
    return httpService({
        url: '/comment',
        method: 'post',
        data: comment
    })
}

export function commentDelete(ids: string) {
    return httpService({
        url: '/comment/batchDelete',
        method: 'delete',
        data: ids,
        headers: {
            'Content-Type': 'text/plain'
        }
    })
}