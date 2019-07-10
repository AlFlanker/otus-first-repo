import Vue from 'vue'

const books = Vue.resource('/book/params')

export default {
    getByParam:prm => books.get({},prm)
}