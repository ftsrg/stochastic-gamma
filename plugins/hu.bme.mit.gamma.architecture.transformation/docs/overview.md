
# Architecture to Gamma transformation

## Steps of transformation

  1. Transform Interfaces -> interfaces package
  1. Transform Subsystems
  1. Transform Mechanical Components
  1. Transform Software Components
  1. Transform System

## SysML to Gamma Mapping

<style>
th {
  background-color: #96D4D4;
}
td {
  background-color: #C6E4E4;
}
</style>

<table>
  <tr><th>SysML</th><th>Gamma</th></tr>
  <tr><td> Interface Block </td><td> Interface </td></tr>
  <tr><td> Port </td><td> Port </td></tr>
  <tr><td> Information Flow </td><td> port -o)- port </td></tr>
  <tr><td> InterfaceConnector </td><td> Component </td></tr>
</table>