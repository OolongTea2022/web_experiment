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

export const userUpdatePassword = async (params) => {
    const res = await myAxios.request({
        url: "/user/update/password",
        method: "POST",
        data: params
    });
    return res;
}

export const userRegister = async (params) => {
    const res = await myAxios.request({
        url: "/user/register",
        method: "POST",
        data: params
    });
    return res;
}

export const getOssUploadCredential = async (fileName,contentType) => {
    const res = await myAxios.request({
        url: "/oss/avatar/upload-credential?fileName="+fileName + "&contentType=" + contentType,
        method: "GET",
    });
    return res;
}
