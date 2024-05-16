import axios,{AxiosInstance,AxiosRequestConfig, InternalAxiosRequestConfig} from "axios";
import { ElMessage } from "element-plus";

//axios配置项
const config = {
    baseURL:'http://localhost:8089', //请求接口的地址
    timeout:10000,

}

//定义返回值类型
export interface Result<T = any> {
    code:number,
    msg:string,
    data:T,

}

class Http{
    //axios的实例
    private instance:AxiosInstance;
    //初始化
    constructor(){
        this.instance = axios.create(config);
        //配置拦截器
        this.interceptors();
    }
    //请求发送，返回之后做处理
    private interceptors(){
                //axios发送请求之前的处理
                this.instance.interceptors.request.use((config:InternalAxiosRequestConfig)=>{
                //在请求头部携带token
                let token='';
                if(token){
                    config.headers!['token'] = token;
                }
                return config;
         }),(error:any) => {
            error.data = {};
            error.data.msg = '服务器异常请联系管理员'
            return error;
         }
         //请求返回之后的处理



    }
}