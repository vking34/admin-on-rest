import {
    GET_LIST,
    GET_ONE,
    GET_MANY,
    GET_MANY_REFERENCE,
    CREATE,
    UPDATE,
    DELETE,
    fetchUtils,
    showNotificationAction
} from 'admin-on-rest';

import { stringify } from 'query-string';

const API_URL = 'http://localhost';
let bizweb_stores_resource = "bizweb-stores";
let users_resource = "users";
let pages_resource = "pages";

const convertRESTRequestToHTTP =  (type, resource, params) => {

    let url = '';
    const options = {};
    options.headers = new Headers({ Accept: 'application/json' });
    const token = localStorage.getItem('token');

    if(token !== null){
        options.headers.set('Authorization', `Bearer ${token}`);
    }

    switch (type){

        case GET_LIST : {

            // console.log(typeof(resource));
            console.log(resource , params);

            let pageRequest = params.pagination.page - 1;
            console.log(params.pagination.page);
            console.log(params.filter.q);
            if(typeof params.filter.q !== "undefined"){
                console.log(true);
                switch (resource){
                    case bizweb_stores_resource : {
                        url = `${API_URL}/${resource}/filter/?alias=${params.filter.q}&&page=${pageRequest}`;
                        break;
                    }

                    case users_resource : {
                        url = `${API_URL}/${resource}/filter/?username=${params.filter.q}&&page=${pageRequest}`;
                        break;
                    }

                    case pages_resource: {
                        url = `${API_URL}/${resource}/filter/?name=${params.filter.q}&&page=${pageRequest}`;
                        break;
                    }
                }

            }else {
                console.log(false);
                url = `${API_URL}/${resource}/?page=${pageRequest}`;
            }

            break;
        }

        case GET_ONE: {

            // switch (resource){
            //     case bizweb_stores_resource : {
            //         url = `${API_URL}/${resource}/?page=${pageRequest}`;
            //         break;
            //     }
            //     case users_resource : {
                    url = `${API_URL}/${resource}/${params.id}`;
                //     break;
                // }
            // }

            break;
        }

        case GET_MANY_REFERENCE: {

            console.log(type, resource, params);
            let pageRequest = params.pagination.page - 1;
            url = `${API_URL}/${params.target}/${params.id}/${resource}?page=${pageRequest}`;

            break;
        }

        case CREATE: {
            url = `${API_URL}/${resource}`;
            options.method = 'POST';
            options.body = JSON.stringify(params.data);
            break;
        }

        case UPDATE: {
            url = `${API_URL}/${resource}/${params.id}`;
            options.method = 'PUT';

            switch (resource){
                case bizweb_stores_resource:{
                    let payload = {password: params.data.password, admin: params.data.admin, active: params.data.active};
                    break;
                }
                case users_resource:{
                    let payload = {HasUpdate: params.data.HasUpdate};
                    break;
                }
            }

            options.body = JSON.stringify(payload);
            break;
        }

        case DELETE: {
            url = `${API_URL}/${resource}/${params.id}`;
            options.method = 'DELETE';
            break;
        }

        default:
            throw new Error('Unsupported fetch action ${type}');

    }

    return {url, options};
};

const convertHTTPResponseToREST = (response, type, resource, params) => {
    const { headers, json } = response;
    switch (type) {
        case GET_LIST:
        {
            switch (resource){
                case bizweb_stores_resource : {
                    return {
                        data: json.content.map(x => {
                            return { ...x, id: x.alias}
                        }),
                        total: json.totalElements
                    }
                }

                case users_resource : {
                    return {
                        data: json.content.map(x => {
                            return { ...x, id: x.username}
                        }),
                        total: json.totalElements
                    };
                }

                case pages_resource: {
                    return {
                        data: json.content.map(x => {
                            return {...x, id: x.id}
                        }),
                        total: json.totalElements
                    }
                }
            }
        }

        // case GET_MANY: {
        //     const query = {
        //         filter: JSON.stringify({ id: params.ids }),
        //     };
        //     url = `${API_URL}/${resource}?${stringify(query)}`;
        //     break;
        // }

        case GET_MANY_REFERENCE : {

            return {
              data : json.content.map(x => {
                  return {...x, id: x.id}
              }),
              total: json.totalElements
            };

            break;
        }

        case CREATE:
        {
            console.log(json);
            if(json.status === true){
                return { data: { ...params.data, id: params.data.username } };
            } else{
                // Promise.reject("Department ID may exist");
                // alert("Department ID may exist");
                // return;
                // showNotification("Department ID may exist");
                showNotificationAction("Department ID may exist");
            }
        }


        default:
            return { data: json };
    }
};

export default (type, resource, params) => {
    const { fetchJson } = fetchUtils;
    const { url, options } = convertRESTRequestToHTTP(type, resource, params);
    return fetchJson(url, options)
        .then(response => convertHTTPResponseToREST(response, type, resource, params));
};