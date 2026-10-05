import React from 'react';

export default function Navbar({ activeView, onNavigate, healthStatus, loading }) {
  const isOnline = healthStatus?.success;

  return (
    <nav className="navbar">
      <div className="brand-logo" onClick={() => onNavigate('landing')} style={{ cursor: 'pointer' }}>
        <div className="brand-icon">⚡</div>
        <span>VyapaarAI</span>
        <span className="brand-tag">AI Advisor</span>
      </div>

      <ul className="nav-links">
        <li>
          <button 
            className={`nav-item-btn ${activeView === 'landing' ? 'active' : ''}`}
            onClick={() => onNavigate('landing')}
          >
            Home
          </button>
        </li>
        <li>
          <button 
            className={`nav-item-btn ${activeView === 'profile' ? 'active' : ''}`}
            onClick={() => onNavigate('profile')}
          >
            Business Profile
          </button>
        </li>
        <li>
          <button 
            className={`nav-item-btn ${activeView === 'analysis' ? 'active' : ''}`}
            onClick={() => onNavigate('analysis')}
          >
            Dashboard
          </button>
        </li>
        <li>
          <button 
            className={`nav-item-btn ${activeView === 'chat' ? 'active' : ''}`}
            onClick={() => onNavigate('chat')}
          >
            🤖 AI Advisor
          </button>
        </li>
      </ul>

      <div className="nav-actions">
        <div className="status-pill">
          <span className={`status-dot ${loading ? 'checking' : isOnline ? 'online' : 'offline'}`}></span>
          <span>{loading ? 'Checking backend...' : isOnline ? 'Backend Online' : 'Backend Offline'}</span>
        </div>

        <button className="btn-primary btn-sm" onClick={() => onNavigate('chat')}>
          <span>Ask AI Advisor</span>
        </button>
      </div>
    </nav>
  );
}
