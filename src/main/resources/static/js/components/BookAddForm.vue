<template>
    <v-card style="width:30%;" class="	justify-content:center;">
        <v-form ref="form" v-model="valid" style="padding: 30px; ">
            <h3>{{this.book?'Форма редактирвания':'Форма добавления новой книги.'}}</h3>
            <v-text-field v-model="title" :rules="titleRules" :counter="30" label="Название">
            </v-text-field>
            <v-text-field v-model="edition" label="Издательство" required>
            </v-text-field>
            <v-textarea
                    name="description"
                    v-model="description"
                    box
                    :rules="[v => v.length<=4096 || 'Поле не должно содержать более 4096 символов!']"
                    :counter="4096"
                    label="Описание книги"
                    auto-grow
                    value="введите краткое описание книги!"
            ></v-textarea>
            <v-select
                    v-model="selected_genres"
                    :items="genresNames"
                    :menu-props="{ maxHeight: '200' }"
                    label="Жанр"
                    multiple
                    hint="Укажите жанры"
                    :rules="[v => !!v || 'Необходимо выбрать жанр']"
                    persistent-hint
                    required
            ></v-select>
            <v-select
                    v-model="selected_authors"
                    :items="authorsByNameAndlastname"
                    :menu-props="{ maxHeight: '200' }"
                    label="Авторы"
                    multiple
                    hint="Выберете авторов"
                    :rules="[v => !!v || 'Необходимо выбрать автора']"
                    persistent-hint
                    required
            ></v-select>
            <div>
                <v-menu
                        ref="menu"
                        v-model="menu"
                        :close-on-content-click="false"
                        :nudge-right="40"
                        lazy
                        transition="scale-transition"
                        offset-y
                        full-width
                        min-width="290px"
                >
                    <template v-slot:activator="{ on }">
                        <v-text-field
                                v-model="date"
                                label="Дата выхода"
                                prepend-icon="event"
                                readonly
                                v-on="on"
                        ></v-text-field>
                    </template>
                    <v-date-picker
                            ref="picker"
                            v-model="date"
                            :max="new Date().toISOString().substr(0, 10)"
                            :rules="[v => !!v || 'Необходимо указать дату']"
                            required
                            min="1000-01-01"
                            locale="Ru-ru"
                            @change="save"
                    ></v-date-picker>
                </v-menu>
            </div>
            <v-btn @click="addBook">{{this.book?'Сохранить изменения':'Добавить'}}</v-btn>
        </v-form>
    </v-card>
</template>

<script>
    import {mapState, mapGetters, mapActions} from 'vuex'

    export default {
        props:["book","editCompl"],
        data() {
            return {
                date: new Date().toISOString().substr(0, 10),
                menu: false,
                form_title:'',
                btn_title:'',
                valid: false,
                description: '',
                title: '',
                edition: '',
                selected_genres: null,
                selected_authors: null,
                items: [],
                titleRules: [
                    v => !!v || 'Имя не может быть пустой',
                    v => v.length <= 30 || 'Не может быть более 30 символов'
                ],

            }
        },
        computed: {
            ...mapState(['genresNames', "authors", "authorsByNameAndlastname"]),
            ...mapGetters(['getAuthors']),
        },
        watch: {
            menu(val) {
                val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
            }
        },
        created(){
            if(this.book){
                this.date = this.book.releaseDate;
                this.title = this.book.title;
                this.edition = this.book.edition;
                this.description = this.book.description;
                this.selected_authors = this.book.authors.map(a=>a.name+" "+a.lastname);
                this.selected_genres = this.book.genres.map(g=>g.genreName);
            }
        },
        methods: {
            async addBook() {
                let res = [];
                if (this.$refs.form.validate()) {
                    this.selected_authors.forEach(line => {
                        let author = this.getAuthors(line);
                        res.push(author[0]);
                    });
                    let new_book = {
                        id:this.book?this.book.id:'',
                        title: this.title,
                        edition: this.edition,
                        description: this.description,
                        releaseDate: this.date,
                        genres: this.selected_genres,
                        authors: res

                    };
                    if( (this.book)){
                        this.updateBook(new_book);
                        this.editCompl();
                    }else {
                        this.saveBook(new_book);
                        this.editCompl();
                    }
                }
            },
            ...mapActions({saveBook: 'addBook',updateBook:'updateBook'}),
            save(date) {
                this.$refs.menu.save(date)
            }
        }
    }
</script>

<style>

</style>