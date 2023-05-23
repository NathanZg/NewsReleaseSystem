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