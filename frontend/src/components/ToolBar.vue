<template>
  <v-card
    color="primary"
    height="auto"
    rounded="0"
    flat
  >
      <v-toolbar density="compact">
  
        <v-toolbar-title>Calendar</v-toolbar-title>
  
        <v-spacer></v-spacer>
  
        <v-btn variant="outlined" class="mx-1" @click="dialogBusca = true">
          <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-dialog v-model="dialogBusca" max-width="600px">
    <v-card>
      <v-card-title>
        <span class="headline">Buscar Evento</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field label="Buscar" v-model="busca"></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="dialogBusca = false">Fechar</v-btn>
        <v-btn color="blue darken-1" text @click="realizarBusca">Buscar</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
        <v-btn variant="outlined" class="mx-1 mr-8" @click="dialog = true">
          Adicionar
        </v-btn>
      </v-toolbar>

      <v-dialog v-model="dialog" max-width="600px">
        <v-card>
          <v-card-title>
            <span class="headline">Adicionar Evento</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" sm="6" md="4">
                  <v-text-field label="Nome" v-model="nome"></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="8">
                  <v-text-field label="Descrição" v-model="descricao"></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" sm="6">
                  <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    :return-value.sync="data"
                    transition="scale-transition"
                    offset-y
                    min-width="290px"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="data"
                        label="Data do Evento"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="data" no-title @input="menu = false"></v-date-picker>
                  </v-menu>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-time-picker v-model="hora" format="24hr"></v-time-picker>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">Fechar</v-btn>
            <v-btn color="blue darken-1" text @click="adicionarEvento">Adicionar</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    dialogBusca: false,
    nome: '',
    descricao: '',
    data: '',
    hora: '',
    menu: false,
    buscar: '',
  }),
  methods: {
    adicionarEvento() {
      //adicionar a lógica para salvar o evento
      this.dialog = false;
    },
    realizarBusca() {
      //adicionar a lógica para realizar a busca
      this.dialogBusca = false;
    },
    abrirDialogBusca() {
      if (!this.dialog) {
        this.dialogBusca = true;
      }
    },
    abrirDialogAdicionar() {
      if (!this.dialogBusca) {
        this.dialog = true;
      }
    },
  },
}
</script>