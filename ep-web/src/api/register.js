import request from '@/utils/request'

export function listAdmin(query) {
  return request({
    url: '/person/register/list',
    method: 'get',
    params: query
  })
}

export function createAdmin(data) {
  return request({
    url: '/person/register/create',
    method: 'post',
    data
  })
}

export function readminAdmin(data) {
  return request({
    url: '/person/register/readmin',
    method: 'get',
    data
  })
}

export function updateAdmin(data) {
  return request({
    url: '/person/register/update',
    method: 'post',
    data
  })
}

export function deleteAdmin(data) {
  return request({
    url: '/person/register/delete',
    method: 'post',
    data
  })
}
