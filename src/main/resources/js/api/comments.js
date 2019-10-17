import Vue from 'vue'

const comments = Vue.resource('/comment{/id}')


export default {
    add: (id,comment) => comments.save({id:id}, comment),
    delete: (id,comment) => comments.remove({id:id},comment)
}