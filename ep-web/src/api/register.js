import request from '@/utils/request'

export function listEmployee(query) {
  return request({
    url: '/person/register/listEmployee',
    method: 'get',
    params: query
  })
}

export function listPerson(query) {
  return request({
    url: '/person/register/list',
    method: 'get',
    params: query
  })
}

export function createPerson(data) {
  return request({
    url: '/person/register/create',
    method: 'post',
    data
  })
}

export function readPerson(id) {
  return request({
    url: '/person/register/read',
    method: 'get',
    params: { id }
  })
}

export function updatePerson(data) {
  return request({
    url: '/person/register/update',
    method: 'post',
    data
  })
}

export function deletePerson(data) {
  return request({
    url: '/person/register/delete',
    method: 'post',
    data
  })
}
