//import './assets/main.css'

import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import {library} from '@fortawesome/fontawesome-svg-core'
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {fas} from '@fortawesome/free-solid-svg-icons'
import {far} from '@fortawesome/free-regular-svg-icons'
import {fab} from '@fortawesome/free-brands-svg-icons'
import Antd from 'ant-design-vue';


import 'ant-design-vue/dist/reset.css';

import App from './App.vue'
import router from './router'

library.add(fas,far,fab);

const app = createApp(App)
app.use(createPinia())
app.use(router)
app.component('font-awesome-icon',FontAwesomeIcon)
app.use(Antd)

app.use(BootstrapVue3)
app.mount('#app')

