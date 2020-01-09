import axios from "axios";


let cookies = document.cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`)) ;

let CSRF_TOKEN = "";

if (cookies !== null){
    CSRF_TOKEN = cookies[1];
}

const axiosInstance = axios.create({
  headers: { "X-XSRF-TOKEN": CSRF_TOKEN }
});

class HttpService{

    API_URL = "/api/v1";

    get(url){
        return axiosInstance.get(url);
    }

    put(url, data){
        return axiosInstance.put(url, data);
    }

    post(url, data){
        return axiosInstance.post(url, data);
    }

    delete(url){
        return axiosInstance.delete(url);
    }

}

var httpServiceInstance = new HttpService();

export default httpServiceInstance;