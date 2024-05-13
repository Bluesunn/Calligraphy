import { createRouter,createWebHistory,RouteRecordRaw } from "vue-router";
import layout from '@/layout/index.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path : '/home',
        name: 'Home',
        component: layout
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default  router