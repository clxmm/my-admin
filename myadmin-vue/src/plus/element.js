import Vue from 'vue'

import {Button, Select,Row,Col,Image,Divider,Form,FormItem,
    Container,Header,Aside,Main,Avatar,Dropdown,DropdownMenu,DropdownItem,Link,
    RadioGroup,RadioButton,Radio,
    Menu,Submenu,MenuItemGroup,MenuItem,
    Input,Message, MessageBox} from 'element-ui';


Vue.use(MenuItem)
Vue.use(MenuItemGroup)
Vue.use(Button)
Vue.use(Submenu)
Vue.use(Menu)
Vue.use(Select)
Vue.use(Row)
Vue.use(Col)
Vue.use(Image)
Vue.use(Divider)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Avatar)
Vue.use(Dropdown)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Link)
Vue.use(Radio)
Vue.use(RadioGroup)
Vue.use(RadioButton)



Vue.prototype.$message = Message
Vue.prototype.$alert = MessageBox.alert