import {
    GET_LIST,
    GET_ONE,
    GET_MANY,
    GET_MANY_REFERENCE,
    CREATE,
    UPDATE,
    DELETE,
    fetchUtils,
} from 'admin-on-rest';

import { stringify } from 'query-string';

const API_URL = 'http://localhost/rest-api';

const convertRESTRequestToHTTP =  (type, resource, params) => {
    //
    // if(localStorage.getItem('token') === null){
    //     return Promise.reject();
    // }

    let url = '';
    const options = {};
    options.headers = new Headers({ Accept: 'application/json' });
    const token = localStorage.getItem('token');
    options.headers.set('Authorization', `Bearer ${token}`);

    switch (type){

        case GET_LIST : {
            const { page, perPage } = params.pagination;
            const { field, order } = params.sort;
            url = `${API_URL}/${resource}`;
            break;
        }

        case GET_ONE: {
            url = `${API_URL}/${resource}/${params.id}`;
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
            options.body = JSON.stringify(params.data);
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
            // let i = -1;
            // const list = json.map(x => {
            //     i++;
            //     return { ...x, id: i}
            // });

            return {
                data: json.map(x => {
                        return { ...x, id: x.department_id}
                    }),
                total: json.length
            };
        }
        case CREATE:
            return { data: { ...params.data, id: json.id } };
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