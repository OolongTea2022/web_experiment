import myAxios from "../utils/request.js"


// 登录
export const userLogin = async (params) => {
    const res = await myAxios.request({
        url: "/user/login",
        method: "POST",
        data: params
    });
    return res;
}