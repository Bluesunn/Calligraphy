import { createApp } from "vue";
import router from "./router/index";
//引入element plus
import ElementPlus from "element-plus";
//element plus样式
import "element-plus/dist/index.css";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import { createPinia } from "pinia";
//import './style.css'
import App from "./App.vue";
//国际化
import zhCn from "element-plus/es/locale/lang/zh-cn";
import myconfirm from "./utils/myconfirm";

const pinia = createPinia();
//createApp(App).mount('#app')
const app = createApp(App);
app.use(router).use(ElementPlus, { locale: zhCn }).use(pinia).mount("#app");
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

//全局挂载
app.config.globalProperties.$myconfirm = myconfirm;
