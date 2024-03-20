<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col>
          <AppCalendar />
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import axios from 'axios';
import AppCalendar from './components/AppCalendar';

export default {
  name: 'App',

  components: {
    AppCalendar,
  },

  data: () => ({
    events: [],
    newEvent: {
      name: '',
      start: '',
      endDate: '',
      details: '',
      color: '',
    },
  }),

  created() {
    this.getEvents();
  },

  methods: {
    getEvents() {
      axios.get('http://localhost:8090/event')
        .then(response => {
          this.events = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },

    createEvent() {
      axios.post('http://localhost:8090/event', this.newEvent)
        .then(() => {
          this.getEvents();
        })
        .catch(error => {
          console.error(error);
        });
    },

    updateEvent(event) {
      axios.put(`http://localhost:8090/event/${event.id}`, event)
        .then(() => {
          this.getEvents();
        })
        .catch(error => {
          console.error(error);
        });
    },

    deleteEvent(event) {
      axios.delete(`http://localhost:8090/event/${event.id}`)
        .then(() => {
          this.getEvents();
        })
        .catch(error => {
          console.error(error);
        });
    },
  },
};
</script>