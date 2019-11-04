import Vue from 'vue'
import VueRouter from 'vue-router'
import BooksList from 'pages/BooksList.vue'
import LoginForm from 'pages/LoginForm.vue'
import CommentPage from "pages/CommentPage.vue"

Vue.use(VueRouter);

const routes  = [
    {path:'/', component: BooksList},
    {path:'/library', component: BooksList},
    {path: '/library/login', component: LoginForm},
    {path:'/library/comments/:id', component: CommentPage},
    {path:'*', component: BooksList},
    ];

export default new VueRouter({
    mode: 'history',
    routes
})