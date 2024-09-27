import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import App from 'pages/App.vue'
import store from 'store/store'
import 'vuetify/dist/vuetify.min.css'
import router from 'router/router'

Vue.use(Vuetify);
new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
});