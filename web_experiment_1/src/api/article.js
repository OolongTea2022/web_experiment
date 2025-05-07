import myAxios from "../utils/request.js"


export const getArticlesById = async (params) => {
    const res = await myAxios.request({
        url: "/article/get_articles_by_id" + "?id="+params,
        method: "GET",
        // data: params
    });
    return res;
}

export const deleteArticle = async (params) => {
    const res = await myAxios.request({
        url: "/article/deleteArticle",
        method: "POST",
        data: params
    });
    return res;
}

export const saveArticle = async (params) => {
    const res = await myAxios.request({
        url: "/article/saveArticle",
        method: "POST",
        data: params
    });
    return res;
}

export const updateArticle = async (params) => {
    const res = await myAxios.request({
        url: "/article/updateArticle",
        method: "POST",
        data: params
    });
    return res;
}

