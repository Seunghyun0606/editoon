import Vue from 'vue'
import VueRouter from 'vue-router'
import MainIndex from '@/views/MainIndex'
import WebtoonIndex from '@/views/WebtoonIndex'
import MyPage from '@/views/MyPage'
import PhotoEditor from '@/views/PhotoEditor'


Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'MainIndex',
    component: MainIndex
  },
  {
    path: '/webtoonindex',
    name: 'WebtoonIndex',
    component: WebtoonIndex
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage
  },
  {
    path: '/photoeditor',
    name: 'PhotoEditor',
    component: PhotoEditor
  },
  // {
    // path: '/about',
    // name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
