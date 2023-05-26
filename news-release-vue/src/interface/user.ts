/**
 * 登录表单提交请求数据类型
 */
export interface LoginReq{
    name:string;
    password:string;
}

/**
 * 注册表单提交请求数据类型
 */
export interface RegisterReq{
    name:string;
    password:string;
    role:string
}