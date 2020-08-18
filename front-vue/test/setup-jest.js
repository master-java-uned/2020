import { config } from '@vue/test-utils';

// Mock Nuxt client-side component
config.stubs['client-only'] = '<div><slot /></div>';