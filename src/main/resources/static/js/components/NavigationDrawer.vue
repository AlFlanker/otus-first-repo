<template>
    <v-navigation-drawer :clipped="clipped" v-model="drawerNav" enable-resize-watcher app dark
                         class="primary lighten-3">
        <v-list-tile>
            <v-list-tile-action></v-list-tile-action>
        </v-list-tile>
        <v-form ref="form_nav" v-model="valid">
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
                        <v-select
                                v-model="selectedAuthors"
                                :items="authorsByNameAndlastname"
                                :menu-props="{ maxHeight: '200' }"
                                label="Выбор"
                                multiple
                                hint="Укажите авторов"
                                persistent-hint
                        ></v-select>
                    </v-list-tile-action>
                </v-list-tile>

                <v-list-tile>
                    <v-list-tile-action></v-list-tile-action>
                </v-list-tile>

                <v-list-tile>
                    <v-list-tile-action>
                        <div>
                            <v-menu
                                    ref="menu_1"
                                    v-model="menu_begin"
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
                                            v-model="date_begin"
                                            label="Дата выхода начало периода"
                                            prepend-icon="event"
                                            readonly
                                            v-on="on"
                                    ></v-text-field>
                                </template>
                                <v-date-picker
                                        ref="picker_begin"
                                        v-model="date_begin"
                                        :max="new Date().toISOString().substr(0, 10)"
                                        :rules="[v => !!v || 'Необходимо указать дату']"
                                        required
                                        min="1000-01-01"
                                        locale="Ru-ru"
                                        @change="save_begin"
                                ></v-date-picker>
                            </v-menu>
                        </div>
                    </v-list-tile-action>
                </v-list-tile>

                <v-list-tile>
                    <v-list-tile-action>
                        <div>
                            <v-menu
                                    ref="menu_2"
                                    v-model="menu_end"
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
                                            v-model="date_end"
                                            label="Дата выхода конец периода"
                                            prepend-icon="event"
                                            readonly
                                            v-on="on"
                                    ></v-text-field>
                                </template>
                                <v-date-picker
                                        ref="picker_end"
                                        v-model="date_end"
                                        :max="new Date().toISOString().substr(0, 10)"
                                        :rules="[v => !!v || 'Необходимо указать дату']"
                                        required
                                        min="1000-01-01"
                                        locale="Ru-ru"
                                        @change="save_end"
                                ></v-date-picker>
                            </v-menu>
                        </div>
                    </v-list-tile-action>
                </v-list-tile>
                <v-flex xs7 offset-xs5 offset-md2 offset-lg5>
                    <v-btn @click="find" small>Показать</v-btn>
                    <v-btn @click="reset" small>Сбросить</v-btn>
                </v-flex>
            </v-list>
        </v-form>
    </v-navigation-drawer>
</template>

<script>
    import {mapState, mapGetters, mapActions} from 'vuex'
    export default {
        props: ['genresNames', "drawerNav"],
        data() {
            return {
                clipped: false,
                menu_begin: false,
                menu_end: false,
                selectedGenres: [],
                selectedAuthors:[],
                date_begin:null,
                date_end:null,
                valid: true
            }
        },
        computed: {
            ...mapState([ "authors", "authorsByNameAndlastname"]),
            ...mapGetters(['getAuthors']),
        },
        watch: {
            drawerNav() {
                this.clipped = !this.drawerNav;
            },
            menu_begin(val) {
                val && setTimeout(() => (this.$refs.picker_begin.activePicker = 'YEAR'))
            },
            menu_end(val) {
                val && setTimeout(() => (this.$refs.picker_end.activePicker = 'YEAR'))
            }

        },
        methods: {
            save_begin: function (date) {
                this.$refs.menu_1.save(date)
            },
            save_end: function (date) {
                this.$refs.menu_2.save(date)
            },
            find: function () {
                if(new Date(this.date_begin) < new Date(this.date_end)){
                   console.log("test")
                }
            },
            reset: function () {
                this.selectedGenres = [];
                this.date_end = null;
                this.date_begin = null;
                this.selectedAuthors = [];
            }
        }
    }
</script>

<style>

</style>