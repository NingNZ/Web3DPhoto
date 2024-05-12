import { createRouter, createWebHistory } from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import HomeView from '@/views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login1',
      component: LoginView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/test',
      name: 'test',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login2',
      component: LoginView
    }
  ]
})
router.beforeEach((to,from,next)=>{
  if(to.path=='/login'){
    next()
  }else{
    const username=sessionStorage.getItem("username")
    if(username==null){
      console.log(username)
      next('/login')
    }else{
      next()
    }
  }
})
export default router
