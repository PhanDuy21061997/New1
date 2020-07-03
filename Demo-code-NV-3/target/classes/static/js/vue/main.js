import Vue from 'vue'
import VueRouter from 'vue-router'
import app from 'index.html'

import routes from './routers'

Vue.config.producationTip = false;


Vue.use(VueRouter);

new Vue({
    render: h => h(app),
    routes
})