<template>
    <div style="margin-top: 2em; width: 70%; overflow: auto">
        <comment-form></comment-form>
        <comment-list></comment-list>
    </div>
</template>

<script>
    import CommentForm from "components/CommentForm.vue";
    import CommentList from "components/CommentList.vue";
    export default {
        components: {CommentList, CommentForm},
        data() {
            return {

            }
        }
    }
</script>

<style>

</style>