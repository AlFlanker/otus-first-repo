import Vue from 'vue'

const authors = Vue.resource('/authors{/id}')

export default {
    add: author => authors.save({}, author),
    update: genre => authors.update({id: author.id}, author),
    remove: id => authors.remove({id}),
    getAll: id=>authors.get({id:id})
}