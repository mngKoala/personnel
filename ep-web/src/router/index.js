import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/Layout'

/** note: Submenu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    perms: ['GET /aaa','POST /bbb']     will control the page perms (you can set multiple perms)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/authredirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  }
]

export const asyncRoutes = [
  {
    path: '/basic',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'hr',
    meta: {
      title: '人力资源',
      icon: 'chart'
    },
    children: [
      {
        path: 'company',
        component: () => import('@/views/basic/company'),
        name: 'company',
        meta: {
          perms: ['GET /basic/company/list', 'POST /basic/company/create', 'POST /basic/company/update', 'POST /basic/company/delete'],
          title: '公司管理',
          noCache: true
        }
      },
      {
        path: 'tenant',
        component: () => import('@/views/basic/tenant'),
        name: 'tenant',
        meta: {
          perms: ['GET /basic/tenant/list', 'POST /basic/tenant/create', 'POST /basic/tenant/update', 'POST /basic/tenant/delete'],
          title: '租户管理',
          noCache: true
        }
      },
      {
        path: 'org',
        component: () => import('@/views/basic/org'),
        name: 'org',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '组织管理',
          noCache: true
        }
      },
      {
        path: 'dept',
        component: () => import('@/views/basic/dept'),
        name: 'dept',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '部门管理',
          noCache: true
        }
      },
      {
        path: 'position',
        component: () => import('@/views/basic/position'),
        name: 'position',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '岗位管理',
          noCache: true
        }
      },
      {
        path: 'employee',
        component: () => import('@/views/basic/employee'),
        name: 'employee',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '员工管理',
          noCache: true
        }
      },
      {
        path: 'positionType',
        component: () => import('@/views/basic/positionType'),
        name: 'positionType',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '岗位类型',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/person',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'personInfo',
    meta: {
      title: '人员信息',
      icon: 'chart'
    },
    children: [
      {
        path: 'stat',
        component: () => import('@/views/person/stat'),
        name: 'stat',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '人员统计',
          noCache: true
        }
      },
      {
        path: 'register',
        component: () => import('@/views/person/register'),
        name: 'register',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '人员登记',
          noCache: true
        }
      },
      {
        path: 'practice',
        component: () => import('@/views/person/practice'),
        name: 'practice',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '实习员工',
          noCache: true
        }
      },
      {
        path: 'ontrial',
        component: () => import('@/views/person/ontrial'),
        name: 'ontrial',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '试用员工',
          noCache: true
        }
      },
      {
        path: 'offline',
        component: () => import('@/views/person/offline'),
        name: 'offline',
        meta: {
          perms: ['GET /person/register/list', 'POST /person/register/create', 'POST /person/register/update', 'POST /person/register/delete'],
          title: '离职人员',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/contract',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'contract',
    meta: {
      title: '人员合同',
      icon: 'chart'
    },
    children: [
      {
        path: 'stat',
        component: () => import('@/views/contract/stat'),
        name: 'stat',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '合同统计',
          noCache: true
        }
      },
      {
        path: 'contract',
        component: () => import('@/views/contract/contract'),
        name: 'contract',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '人员合同',
          noCache: true
        }
      },
      {
        path: 'org',
        component: () => import('@/views/contract/org'),
        name: 'org',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '合同签订方',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/salary',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'salary',
    meta: {
      title: '薪酬管理',
      icon: 'chart'
    },
    children: [
      {
        path: 'payroll',
        component: () => import('@/views/salary/payroll'),
        name: 'payroll',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '薪酬发放',
          noCache: true
        }
      },
      {
        path: 'parameter',
        component: () => import('@/views/salary/parameter'),
        name: 'parameter',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '薪酬参数',
          noCache: true
        }
      },
      {
        path: 'month',
        component: () => import('@/views/salary/month'),
        name: 'month',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '薪酬月份',
          noCache: true
        }
      },
      {
        path: 'template',
        component: () => import('@/views/salary/template'),
        name: 'template',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '薪酬模板',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/attendance',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'attendance',
    meta: {
      title: '考勤管理',
      icon: 'chart'
    },
    children: [
      {
        path: 'attendance',
        component: () => import('@/views/attendance/attendance'),
        name: 'attendance',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '人员考勤',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/recruit',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'recruit',
    meta: {
      title: '招聘管理',
      icon: 'chart'
    },
    children: [
      {
        path: 'position',
        component: () => import('@/views/recruit/position'),
        name: 'position',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '招聘岗位',
          noCache: true
        }
      },
      {
        path: 'record',
        component: () => import('@/views/recruit/record'),
        name: 'record',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '招聘记录',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/file',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'file',
    meta: {
      title: '档案管理',
      icon: 'chart'
    },
    children: [
      {
        path: 'file',
        component: () => import('@/views/file/file'),
        name: 'file',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '人员档案',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/sys',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'sysManage',
    meta: {
      title: '系统管理',
      icon: 'chart'
    },
    children: [
      {
        path: 'admin',
        component: () => import('@/views/sys/admin'),
        name: 'admin',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '管理员',
          noCache: true
        }
      },
      {
        path: 'notice',
        component: () => import('@/views/sys/notice'),
        name: 'sysNotice',
        meta: {
          perms: ['GET /admin/notice/list', 'POST /admin/notice/create', 'POST /admin/notice/update', 'POST /admin/notice/delete'],
          title: '通知管理',
          noCache: true
        }
      },
      {
        path: 'log',
        component: () => import('@/views/sys/log'),
        name: 'log',
        meta: {
          perms: ['GET /admin/log/list'],
          title: '操作日志',
          noCache: true
        }
      },
      {
        path: 'role',
        component: () => import('@/views/sys/role'),
        name: 'role',
        meta: {
          perms: ['GET /admin/role/list', 'POST /admin/role/create', 'POST /admin/role/update', 'POST /admin/role/delete', 'GET /admin/role/permissions', 'POST /admin/role/permissions'],
          title: '角色管理',
          noCache: true
        }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
