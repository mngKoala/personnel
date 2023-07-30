import request from '@/utils/request'

export function tree() {
  return request({
    url: '/basic/org/tree',
    method: 'get'
  })
}

export function getName(id) {
  return request({
    url: '/basic/org/name',
    method: 'get',
    params: {id}
  })
}

export function getCompany() {
  return request({
    url: '/basic/org/com',
    method: 'get'
  })
}

export function getDept(query) {
  return request({
    url: '/basic/org/dept',
    method: 'get',
    params: query
  })
}

export function listOrg(query) {
  return request({
    url: '/basic/org/list',
    method: 'get',
    params: query
  })
}

export function createOrg(data) {
  return request({
    url: '/basic/org/create',
    method: 'post',
    data
  })
}

export function readminOrg(data) {
  return request({
    url: '/basic/org/readmin',
    method: 'get',
    data
  })
}

export function updateOrg(data) {
  return request({
    url: '/basic/org/update',
    method: 'post',
    data
  })
}

export function deleteOrg(data) {
  return request({
    url: '/basic/org/delete',
    method: 'post',
    data
  })
}