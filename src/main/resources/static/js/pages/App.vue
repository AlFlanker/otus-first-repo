<template>
    <v-app>
        <v-navigation-drawer :clipped="clipped" v-model="drawerNav" enable-resize-watcher app dark class="primary lighten-3">
            <v-list-tile>
                <v-list-tile-action></v-list-tile-action>
            </v-list-tile>

            <v-list>
                <v-list-tile>
                    <v-list-tile-action>
                        <v-select
                                v-model="selectedGenres"
                                :items="genresNames"
                                :menu-props="{ maxHeight: '200' }"
                                label="Выбор"
                                multiple
                                hint="Укажите жанры"
                                persistent-hint
                        ></v-select>
                    </v-list-tile-action>
                </v-list-tile>

                <v-list-tile>
                    <v-list-tile-action></v-list-tile-action>
                </v-list-tile>

                <v-list-tile>
                    <v-list-tile-action>
                        <v-menu ref="menu" v-model="menu" :close-on-content-click="false" :nudge-right="40" :return-value.sync="date" lazy transition="scale-transition" offset-y full-width max-width="290px" min-width="290px">
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
                                    v-model="date"
                                    type="month"
                                    no-title
                                    scrollable
                            >
                                <v-spacer></v-spacer>
                                <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
                                <v-btn flat color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                            </v-date-picker>
                        </v-menu>
                    </v-list-tile-action>
                </v-list-tile>
            </v-list>
        </v-navigation-drawer>
        <v-toolbar fixed app :clipped-left="clipped">
            <v-toolbar-side-icon @click.stop="drawerNav = !drawerNav"></v-toolbar-side-icon>
            <v-toolbar-title>Библиотека</v-toolbar-title>
            <v-spacer></v-spacer>

        </v-toolbar>
        <v-content>
            <v-container fluid>
                    <books-list :books="books"/>
            </v-container>
        </v-content>
        <v-footer class="pa-3">
            <v-spacer></v-spacer>
            <div>&copy; {{ new Date().getFullYear() }}</div>
        </v-footer>
    </v-app>
</template>

<script>
    import BooksList from 'components/BooksList.vue'
    import booksApi from 'api/books'
    import genresApi from 'api/genres'

    export default {
        components: {
            BooksList
        },
        data() {
            return {
                date: new Date().toISOString().substr(0, 7),
                menu: false,
                modal: false,
                books: null,
                genres: null,
                genresNames: [],
                selectedGenres:[],
                drawerNav: false,
                clipped: false,
                e7: []
            }
        },
        created() {
                booksApi.getAll()
                    .then(result =>
                        result.json().then(data => {
                            console.log(data)
                            this.books = data
                        }))

                genresApi.getAll()
                    .then(result =>
                        result.json().then(data => {
                            console.log(data)
                            this.genres = data
                            this.genres.forEach(g => this.genresNames.push(g.genreName))
                        }))


        },
    }
</script>

<style>

</style>