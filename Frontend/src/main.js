//import './assets/main.css'
import BootstrapVue from 'bootstrap-vue-3'
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import { createApp } from "vue";
import { createPinia } from "pinia";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";

import Antd from "ant-design-vue";

import "ant-design-vue/dist/reset.css";

import App from "./App.vue";
import router from "./router";

library.add(fas, far, fab);

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(BootstrapVue);
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(Antd);

app.mount("#app");
