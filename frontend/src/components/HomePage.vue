<template>
    <div class="hello">
      <h1>{{ msg }}</h1>
      <table v-if="smartPlugs.length" class="smart-plugs-table">
        <thead>
          <tr>
            <th>Device Name</th>
            <th>Current Power</th>
            <th>Total Power Consumption</th>
            <th>State</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="plug in smartPlugs" :key="plug.id">
            <td>{{ plug.deviceName }}</td>
            <td>{{ plug.currentPower }}</td>
            <td>{{ plug.totalPowerConsumption }}</td>
            <td>{{ plug.state }}</td>
            <td>
              <button @click="toggleState(plug)" :class="{ 'button-on': plug.state === 'ON', 'button-off': plug.state === 'OFF' }">
                {{ plug.state === 'ON' ? 'Turn OFF' : 'Turn ON' }}
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Home-Page',
    props: {
      msg: String
    },
    data() {
      return {
        smartPlugs: []
      };
    },
    created() {
      this.fetchSmartPlugs();
    },
    methods: {
      fetchSmartPlugs() {
        fetch('http://localhost:8080/smartPlug')
          .then(response => response.json())
          .then(data => {
            this.smartPlugs = data;
          })
          .catch(error => {
            console.error('Error fetching smart plugs:', error);
          });
      },
      toggleState(plug) {
        const newState = plug.state === 'ON' ? 'OFF' : 'ON';
        fetch('http://localhost:8080/smartPlug/onOff', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ id: plug.id, state: newState })
        })
          .then(() => {
            this.fetchSmartPlugs();
          })
          .catch(error => {
            console.error('Error toggling state:', error);
          });
      }
    }
  };
  </script>
  
  <style scoped>
  .smart-plugs-table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .smart-plugs-table th,
  .smart-plugs-table td {
    border: 1px solid #ddd;
    padding: 8px;
  }
  
  .smart-plugs-table th {
    background-color: #f2f2f2;
    text-align: left;
  }
  
  button {
    padding: 5px 10px;
    cursor: pointer;
  }
  
  .button-on {
    background-color: green;
    color: white;
  }
  
  .button-off {
    background-color: red;
    color: white;
  }
  </style>
  