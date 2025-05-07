import myAxios from "../utils/request.js"


// 获取所有联系人
export const getAllUserContact = async (params) => {
    const res = await myAxios.request({
        url: "/user_contact/get_all",
        method: "GET",
        // data: params
    });
    return res;
}


export const saveUserContact = async (params) => {
    const res = await myAxios.request({
        url: "/user_contact/saveUserContact",
        method: "POST",
        data: params
    });
    return res;
}

export const deleteUserContact = async (params) => {
    const res = await myAxios.request({
        url: "/user_contact/deleteUserContact",
        method: "POST",
        data: params
    });
    return res;
}

export const updateUserContact = async (params) => {
    const res = await myAxios.request({
        url: "/user_contact/updateUserContact",
        method: "POST",
        data: params
    });
    return res;
}



